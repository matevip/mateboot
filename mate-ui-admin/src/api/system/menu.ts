import http from '@/utils/request'

export const useAuthorityList = () => {
	return http.get('/sys/menu/authority')
}

export const useMenuNav = () => {
	return http.get('/sys/menu/route')
}

export const useMenuList = (type: Number) => {
	// 菜单类型 0：菜单  1：按钮  2：IFrame 3: link -1 除按钮外的所有
	const menuType = type === 1 ? -1 : type

	return http.get('/sys/menu/list?type=' + menuType)
}

export const useButtonList = (id: Number) => {
	return http.get('/sys/menu/buttons?id=' + id)
}

export const useMenuSave = (dataForm: any) => {
	return http.post('/sys/menu/saveAll', dataForm)
}

export const useMenuDel = (ids: any) => {
	return http.post('/sys/menu/deleteAll', ids)
}