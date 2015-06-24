##这里放 Controllers [ extend ControllerExt ]

注意： 
1.所有的 view 跳转对应的 action 方法命名为对应的页面名称 V。 比如登录页面为 loginV。对应的 action（form 的 action） 方法为 login；
2.所有的 URI 在 jsp 中全部为小写，在 java 中按照驼峰法命名（其全部小写之后就是对应的 URI）。
3. 建议extends com.jfinal.ext2.core.ControllerExt，在这个com.jfinal.ext2.core.ControllerExt中加入了对多个文件上传FileRenamePolicy的处理。