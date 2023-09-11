<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="500" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" :disabled="mode == 'show'" ref="dialogFormRef" label-width="100px"
			label-position="left">
			<!-- <el-form-item label="头像" prop="avatar">
				<m-upload v-model="form.avatar" title="上传头像"></m-upload>
			</el-form-item> -->
			<el-form-item label="登录账号" prop="username">
				<el-input v-model="form.username" placeholder="用于登录系统" clearable></el-input>
			</el-form-item>
			<el-form-item label="姓名" prop="realName">
				<el-input v-model="form.realName" placeholder="请输入完整的真实姓名" clearable></el-input>
			</el-form-item>
			<template v-if="mode == 'add'">
				<el-form-item label="登录密码" prop="password">
					<el-input type="password" v-model="form.password" clearable show-password></el-input>
				</el-form-item>
				<el-form-item label="确认密码" prop="password2">
					<el-input type="password" v-model="form.password2" clearable show-password></el-input>
				</el-form-item>
			</template>
			<el-form-item label="手机号码" prop="mobile">
				<el-input v-model="form.mobile" placeholder="请输入手机号码" clearable></el-input>
			</el-form-item>
			<el-form-item label="邮箱" prop="email">
				<el-input v-model="form.email" placeholder="请输入邮箱" clearable></el-input>
			</el-form-item>
			<el-form-item label="所属部门" prop="deptId">
				<el-cascader v-model="form.deptId" :options="depts" :props="deptsProps" clearable show-all-levels="false"
					style="width: 100%;"></el-cascader>
			</el-form-item>
			<!-- <el-form-item label="所属角色" prop="roleIdList">
				<el-select v-model="form.roleList" multiple filterable style="width: 100%">
					<el-option v-for="item in groups" :key="item.id" :label="item.name" :value="item.id" />
				</el-select>
			</el-form-item> -->
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取 消</el-button>
			<el-button v-if="mode != 'show'" type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup name="userSave">
import { ref, reactive, onBeforeMount } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useUserSubmit } from '@/api/system/user'
import { useDeptList } from '@/api/system/dept'
// import { useRoleList } from '@/api/system/role'
import MUpload from '@/components/MUpload/index.vue'
import { isArray } from '@/utils/is'

const emit = defineEmits(['success', 'closed'])
const mode = ref("add")
const titleMap: any = reactive({
	add: '新增用户',
	edit: '编辑用户',
	show: '查看'
})
const visible = ref(false)
const isSaveing = ref(false)
//表单数据
const form = reactive({
	id: "",
	username: "",
	avatar: "",
	realName: "",
	deptId: "",
	password: "",
	password2: "",
	mobile: "",
	email: "",
	superAdmin: 0,
	gender: 0,
	status: 1,
	roleList: []
})
//验证规则
const rules = reactive({
	avatar: [{ required: true, message: '请上传头像' }],
	username: [{ required: true, message: '请输入登录账号' }],
	realName: [{ required: true, message: '请输入真实姓名' }],
	mobile: [{ required: true, message: '请输入手机号码' }],
	password: [
		{ required: true, message: '请输入登录密码' },
		{
			validator: (rule: any, value: any, callback: any) => {
				if (form.password2 !== '') {
					dialogFormRef.value.validateField('password2');
				}
				callback();
			}
		}
	],
	password2: [
		{ required: true, message: '请再次输入密码' },
		{
			validator: (rule: any, value: any, callback: any) => {
				if (value !== form.password) {
					callback(new Error('两次输入密码不一致!'));
				} else {
					callback();
				}
			}
		}
	],
	deptId: [
		{ required: true, message: '请选择所属部门' }
	],
})

onBeforeMount(() => {
	getGroup()
	getDept()
})

type TreeNode = {
	id: number;
	parentId?: number | null;
	label: string;
	children?: TreeNode[];
};

//所需数据选项
const groups = ref([])
const depts = ref<TreeNode[]>([]);
const deptsProps = reactive({
	value: "id",
	checkStrictly: true
})

const dialogFormRef = ref()

//显示
const open = (data = 'add') => {
	mode.value = data;
	visible.value = true;
}

//加载树数据
const getGroup = async () => {
	// var res = await useRoleList();
	// groups.value = res.data
}
const getDept = async () => {
	var res = await useDeptList();
	depts.value = convertData(res.data);
}

// 临时方法做转换dept数据格式
function convertData(data: TreeNode[]): TreeNode[] {
	return data.map((node: any) => {
		const newNode: TreeNode = {
			id: node.id,
			parentId: node.pid ?? node.parentId,
			label: node.name ?? node.label,
		};
		if (node.children) {
			newNode.children = convertData(node.children);
		}
		return newNode;
	});
}

//表单提交方法
const submit = () => {
	dialogFormRef.value.validate(async (valid: any) => {
		if (valid) {
			if (isArray(form.deptId)) {
				form.deptId = form.deptId[form.deptId.length - 1]
			}
			if (form.password == '') {
				delete form.password
				delete form.password2
			}
			isSaveing.value = true;
			var res: any = await useUserSubmit(form);
			isSaveing.value = false;
			if (res.code == 0) {
				emit('success', form, mode.value)
				visible.value = false;
				ElMessage.success(res.msg)
			} else {
				ElMessageBox.alert(res.msg, "提示", { type: 'error' })
			}
		} else {
			return false;
		}
	})
}
//表单注入数据
const setData = (data: any) => {
	Object.assign(form, data)
}
//提供给父组件调用
defineExpose({
	open,
	setData
})

</script>

<style></style>
