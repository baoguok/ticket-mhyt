import request from '@/utils/request'

export function list(query) {
  return request({
    url: '/statis/list',
    method: 'get',
    params: query
  })
}
