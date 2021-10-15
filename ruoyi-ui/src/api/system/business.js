import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listBusiness(query) {
  return request({
    url: '/system/business/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getBusiness(id) {
  return request({
    url: '/system/business/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addBusiness(data) {
  return request({
    url: '/system/business',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateBusiness(data) {
  return request({
    url: '/system/business',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delBusiness(id) {
  return request({
    url: '/system/business/' + id,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportBusiness(query) {
  return request({
    url: '/system/business/export',
    method: 'get',
    params: query
  })
}