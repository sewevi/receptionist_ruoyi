import request from '@/utils/request'

// 查询仓库调拨记录列表
export function listAllocation(query) {
  return request({
    url: '/api/allocation/list',
    method: 'get',
    params: query
  })
}

// 查询仓库调拨记录详细
export function getAllocation(id) {
  return request({
    url: '/api/allocation/' + id,
    method: 'get'
  })
}

// 新增仓库调拨记录
export function addAllocation(data) {
  return request({
    url: '/api/allocation',
    method: 'post',
    data: data
  })
}

// 修改仓库调拨记录
export function updateAllocation(data) {
  return request({
    url: '/api/allocation',
    method: 'put',
    data: data
  })
}

// 删除仓库调拨记录
export function delAllocation(id) {
  return request({
    url: '/api/allocation/' + id,
    method: 'delete'
  })
}

// 导出仓库调拨记录
export function exportAllocation(query) {
  return request({
    url: '/api/allocation/export',
    method: 'get',
    params: query
  })
}
