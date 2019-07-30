import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/ticgood/list',
    method: 'post',
    data
  })
}

export function create(data) {
  return request({
    url: '/ticgood/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/ticgood/update',
    method: 'post',
    data
  })
}

export function delect(data) {
  return request({
    url: '/ticgood/delete',
    method: 'post',
    data
  })
}
