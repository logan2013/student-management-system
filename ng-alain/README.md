# ng-alain

一套基于 [Ng-zorro-antd](https://github.com/NG-ZORRO/ng-zorro-antd)【ANT DESIGN】 的企业后台模板。



### 常见问题及解决方案

##### 访问服务端

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



##### 前端 token 存储策略

修改 delon.module.ts 

```typescript
// 添加
{
	provide: DA_STORE_TOKEN,
	useClass: SessionStorageStore,
}
```





### 特性

+ 基于 `ng-zorro-antd`
+ 响应式
+ 国际化
+ ACL访问控制
+ 延迟加载及良好的启用画面
+ 良好的UI路由设计
+ 十种颜色版本
+ Less预编译
+ 良好的目录组织结构
+ 简单升级
+ 模块热替换
+ 支持Docker部署
+ 支持[Electron](http://ng-alain.com/docs/cli#electron)打包（限cli构建）



### Links

- [文档](http://ng-alain.com)
- [@delon](https://github.com/cipchk/delon)
- [DEMO](https://cipchk.github.io/ng-alain/)