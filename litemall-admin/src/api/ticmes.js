import request from '@/utils/request'

export function list(query) {
  return request({
    url: '/message/list',
    method: 'get',
    params: query
  })
}

export function create(data) {
  return request({
    url: '/message/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/message/update',
    method: 'post',
    data
  })
}

export function delect(data) {
  return request({
    url: '/message/delete',
    method: 'post',
    data
  })
}
