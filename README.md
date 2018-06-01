# student-management-system
学习管理系统



修改 ng-alain 启动端口为 4300 

在 dev-server/schema.json 中修改

```json
    "port": {
      "type": "number",
      "description": "Port to listen on.",
      "default": 4300
    },
```



访问服务端

修改 default.interceptor.ts 

```typescript
// 统一加上服务端前缀
let url = req.url;
if (url.startsWith('assets')) {
	url = '/' + url;
}
if (!url.startsWith('https://') && !url.startsWith('http://') && !url.startsWith('/assets')) {
	url = environment.SERVER_URL + url;
}
```



前端 token 存储策略

修改 delon.module.ts 

```typescript
// 添加
{
	provide: DA_STORE_TOKEN,
	useClass: SessionStorageStore,
}
```



JSON 格式化出错， 因为出现无限循环， 在转 JSON 时，忽略这些字段就可以了 ，类上注解

@JsonIgnoreProperties(value={"roles"})