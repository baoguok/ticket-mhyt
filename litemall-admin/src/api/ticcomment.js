import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/ticComment/list',
    method: 'post',
    data
  })
}

export function create(data) {
  return request({
    url: '/ticComment/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/ticComment/update',
    method: 'post',
    data
  })
}

export function delect(data) {
  return request({
    url: '/ticComment/delete',
    method: 'post',
    data
  })
}
