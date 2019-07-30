import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/ticorder/list',
    method: 'post',
    data
  })
}

export function create(data) {
  return request({
    url: '/ticorder/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/ticorder/update',
    method: 'post',
    data
  })
}

export function delect(data) {
  return request({
    url: '/ticorder/delete',
    method: 'post',
    data
  })
}
