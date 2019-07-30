import request from '@/utils/request'

export function listGate(query) {
  return request({
    url: '/ticGateView/list',
    method: 'get',
    params: query
  })
}

export function createGate(data) {
  return request({
    url: '/ticGateView/create',
    method: 'post',
    data
  })
}

export function updateGate(data) {
  return request({
    url: '/ticGateView/update',
    method: 'post',
    data
  })
}

export function deleteGate(data) {
  return request({
    url: '/ticGateView/delete',
    method: 'post',
    data
  })
}
