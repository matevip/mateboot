<template>
    <div class="m-statistic">
        <div class="m-statistic-title">
            {{ title }}
            <el-tooltip v-if="tips" effect="light">
                <template #content>
                    <div style="width: 200px;line-height: 2;">
                        {{ tips }}
                    </div>
                </template>
                <el-icon class="m-statistic-tips"><el-icon-question-filled /></el-icon>
            </el-tooltip>
        </div>
        <div class="m-statistic-content">
            <span v-if="prefix" class="m-statistic-content-prefix">{{ prefix }}</span>
            <span class="m-statistic-content-value">{{ cmtValue }}</span>
            <span v-if="suffix" class="m-statistic-content-suffix">{{ suffix }}</span>
        </div>
        <div v-if="description || $slots.default" class="m-statistic-description">
            <slot>
                {{ description }}
            </slot>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { computed } from 'vue'
import { groupSeparator } from '@/utils'

const props = defineProps({
    title: {
        type: String,
        required: true,
        default: ''
    },
    value: {
        type: String,
        require: true,
        default: ''
    },
    prefix: {
        type: String,
        default: ''
    },
    suffix: {
        type: String,
        default: ''
    },
    description: {
        type: String,
        default: ''
    },
    tips: {
        type: String,
        default: ''
    },
    groupSeparator: {
        type: Boolean,
        default: ''
    },
})

const cmtValue = computed(() => {
    return props.groupSeparator ? groupSeparator(props.value) : props.value
})
</script>

<style scoped>
.m-statistic-title {
    font-size: 12px;
    color: #999;
    margin-bottom: 10px;
    display: flex;
    align-items: center;
}

.m-statistic-tips {
    margin-left: 5px;
}

.m-statistic-content {
    font-size: 20px;
    color: #333;
}

.m-statistic-content-value {
    font-weight: bold;
}

.m-statistic-content-prefix {
    margin-right: 5px;
}

.m-statistic-content-suffix {
    margin-left: 5px;
    font-size: 12px;
}

.m-statistic-description {
    margin-top: 10px;
    color: #999;
}

.dark .m-statistic-content {
    color: #d0d0d0;
}
</style>
