<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="400" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" ref="dialogFormRef" label-width="100px" label-position="left">
			<el-form-item label="字典分类" prop="dictId">
				<el-cascader v-model="form.dictId" :options="dic" :props="dicProps" :show-all-levels="false"
					clearable></el-cascader>
			</el-form-item>
			<el-form-item label="项名称" prop="name">
				<el-input v-model="form.dictName" clearable></el-input>
			</el-form-item>
			<el-form-item label="键值" prop="key">
				<el-input v-model="form.dictValue" clearable></el-input>
			</el-form-item>
			<el-form-item label="状态" prop="status">
				<el-radio-group v-model="form.status">
					<el-radio :label="'1'">正常</el-radio>
					<el-radio :label="'0'">禁用</el-radio>
				</el-radio-group>
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取 消</el-button>
			<el-button type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>
	</el-dialog>
</template>
<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useDictList, useDictItemSave } from '@/api/system/dict';


const emit = defineEmits(['success', 'closed'])

const mode = ref("add")
const titleMap: any = ref({
	add: '新增字典项',
	edit: '编辑字典项'
})

const visible = ref(false)
const isSaveing = ref(false)

//表单数据
const form = reactive({
	id: "",
	dictId: "",
	dictName: "",
	dictValue: "",
	status: '1',
	sort: 0
})

//验证规则
const rules = reactive({
	dictName: [
		{ required: true, message: '请输入字典项名称' }
	],
	dictValue: [
		{ required: true, message: '请输入字典项值' }
	]
})

const dic = ref([])
const dicProps = ref({
	value: "id",
	label: "dictName",
	emitPath: false,
	checkStrictly: true
})


const dialogFormRef = ref()

onMounted(() => {
	// if(this.params){
	// 			this.form.dic = this.params.code
	// 		}
	getDic()
})
//显示
const open = (data = 'add') => {
	mode.value = data;
	visible.value = true;
}

//获取字典列表
const getDic = async () => {
	var res = await useDictList();
	dic.value = res.data.list;
}

//表单提交方法
const submit = () => {
	dialogFormRef.value.validate(async (valid: boolean) => {
		if (valid) {
			isSaveing.value = true;
			var res: any = await useDictItemSave(form);
			isSaveing.value = false;
			if (res.code == 0) {
				emit('success', form, mode.value)
				visible.value = false;
				ElMessage.success(res.msg)
			} else {
				ElMessageBox.alert(res.msg, "提示", { type: 'error' })
			}
		}
	})
}

//表单注入数据
const setData = (data: any) => {
	//可以和上面一样单个注入，也可以像下面一样直接合并进去
	Object.assign(form, data)
}

console.log(form)

defineExpose({
	open,
	setData
})

</script>
<!-- <script>
	export default {
		emits: ['success', 'closed'],
		data() {
			return {
				mode: "add",
				titleMap: {
					add: '新增项',
					edit: '编辑项'
				},
				visible: false,
				isSaveing: false,
				form: {
					id: "",
					dic: "",
					name: "",
					key: "",
					yx: "1"
				},
				rules: {
					dic: [
						{required: true, message: '请选择所属字典'}
					],
					name: [
						{required: true, message: '请输入项名称'}
					],
					key: [
						{required: true, message: '请输入键值'}
					]
				},
				dic: [],
				dicProps: {
					value: "id",
					label: "name",
					emitPath: false,
					checkStrictly: true
				}
			}
		},
		mounted() {
			if(this.params){
				this.form.dic = this.params.code
			}
			this.getDic()
		},
		methods: {
			//显示
			open(mode='add'){
				this.mode = mode;
				this.visible = true;
				return this;
			},
			//获取字典列表
			async getDic(){
				var res = await this.$API.system.dic.tree.get();
				this.dic = res.data;
			},
			//表单提交方法
			submit(){
				this.$refs.dialogForm.validate(async (valid) => {
					if (valid) {
						this.isSaveing = true;
						var res = await this.$API.demo.post.post(this.form);
						this.isSaveing = false;
						if(res.code == 200){
							this.$emit('success', this.form, this.mode)
							this.visible = false;
							this.$message.success("操作成功")
						}else{
							this.$alert(res.message, "提示", {type: 'error'})
						}
					}
				})
			},
			//表单注入数据
			setData(data){
				this.form.id = data.id
				this.form.name = data.name
				this.form.key = data.key
				this.form.yx = data.yx
				this.form.dic = data.dic
			}
		}
	}
</script> -->

<style></style>
