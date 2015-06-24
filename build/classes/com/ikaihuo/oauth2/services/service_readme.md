##这里放 Services

使用方法，所有的 Service extends com.jfinal.ext2.core.Service。在对应的 Controller 中定义一个 service 即可
如：

public class UserController extends ControllerExt {
	
	private String viewPath = "view";
	
	private UserService userService;
...
}


也可以使用JFinal2.0的Duang和Enhancer,具体看2.0 doc。