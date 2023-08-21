<template>
    <div class="logo-bar">
        <img class="logo" src="/img/logo.png">
        <span>{{ appName }}</span>
    </div>
</template>
    
<script setup lang='ts'>

defineProps<{
	appName?: string
}>()

</script>
