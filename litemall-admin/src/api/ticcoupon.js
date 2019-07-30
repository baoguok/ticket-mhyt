import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/ticcoupon/list',
    method: 'post',
    data
  })
}

export function create(data) {
  return request({
    url: '/ticcoupon/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/ticcoupon/update',
    method: 'post',
    data
  })
}

export function delect(data) {
  return request({
    url: '/ticcoupon/delete',
    method: 'post',
    data
  })
}
