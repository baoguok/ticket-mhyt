import request from '@/utils/request'

export function listadCate(data) {
  return request({
    url: '/adCate/list',
    method: 'post',
    data
  })
}

export function create(data) {
  return request({
    url: '/adCate/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/adCate/update',
    method: 'post',
    data
  })
}

export function delect(data) {
  return request({
    url: '/adCate/delete',
    method: 'post',
    data
  })
}
