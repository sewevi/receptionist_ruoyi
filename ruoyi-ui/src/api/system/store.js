import request from '@/utils/request'

// 查询仓库列表
export function listStore(query) {
  return request({
    url: '/api/store/list',
    method: 'get',
    params: query
  })
}

// 查询仓库详细
export function getStore(id) {
  return request({
    url: '/api/store/' + id,
    method: 'get'
  })
}

// 新增仓库
export function addStore(data) {
  return request({
    url: '/api/store',
    method: 'post',
    data: data
  })
}

// 修改仓库
export function updateStore(data) {
  return request({
    url: '/api/store',
    method: 'put',
    data: data
  })
}

// 删除仓库
export function delStore(id) {
  return request({
    url: '/api/store/' + id,
    method: 'delete'
  })
}

// 导出仓库
export function exportStore(query) {
  return request({
    url: '/api/store/export',
    method: 'get',
    params: query
  })
}
