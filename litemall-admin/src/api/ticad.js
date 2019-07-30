import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/ticad/list',
    method: 'post',
    data
  })
}

export function create(data) {
  return request({
    url: '/ticad/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/ticad/update',
    method: 'post',
    data
  })
}

export function delect(data) {
  return request({
    url: '/ticad/delete',
    method: 'post',
    data
  })
}
