import request from '@/utils/request'

export function fetchList(data) {
  return request({
    url: '/ticuser/list',
    method: 'post',
    data
  })
}

export function createUser(data) {
  return request({
    url: '/ticuser/create',
    method: 'post',
    data
  })
}

export function readUser(data) {
  return request({
    url: '/ticuser/detail',
    method: 'get',
    data
  })
}

export function updateUser(data) {
  return request({
    url: '/ticuser/update',
    method: 'post',
    data
  })
}

export function listAddress(query) {
  return request({
    url: '/address/list',
    method: 'get',
    params: query
  })
}

export function listCollect(query) {
  return request({
    url: '/collect/list',
    method: 'get',
    params: query
  })
}

export function listFeedback(data) {
  return request({
    url: '/ticback/list',
    method: 'post',
    data
  })
}

export function listFootprint(query) {
  return request({
    url: '/footprint/list',
    method: 'get',
    params: query
  })
}

export function listHistory(query) {
  return request({
    url: '/history/list',
    method: 'get',
    params: query
  })
}

export function listTourist(data) {
  return request({
    url: '/tourist/list',
    method: 'post',
    data
  })
}
