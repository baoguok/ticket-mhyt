import request from '@/utils/request'

export function listCate(data) {
  return request({
    url: '/cate/list',
    method: 'post',
    data
  })
}

export function create(data) {
  return request({
    url: '/cate/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/cate/update',
    method: 'post',
    data
  })
}

export function delect(data) {
  return request({
    url: '/cate/delete',
    method: 'post',
    data
  })
}
