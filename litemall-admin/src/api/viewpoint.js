import request from '@/utils/request'

export function listView(query) {
  return request({
    url: '/view/list',
    method: 'get',
    params: query
  })
}

export function create(data) {
  return request({
    url: '/view/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/view/update',
    method: 'post',
    data
  })
}

export function delect(data) {
  return request({
    url: '/view/delete',
    method: 'post',
    data
  })
}
