<h2>
SpringBoot+Mybatis+JSP+c3p0+springmvc+spring+dtgrid
</h2>
</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由于本人第一次使用springboot搭建项目，过程中有不足之处还请各位大神指教。项目中使用mysql，c3p0，mybatis，jsp,springmvc,dtgrid搭建的简单demo。项目中包含用户角色权限管理，登陆拦截过滤器，初始化监听器，全局异常统一处理，全局环境的静态变量配置，以及aop事务拦截处理.</br>
</br>
下面简单介绍一下目录结构：</br>
<article class="markdown-body entry-content" itemprop="text">
<h4>该项目所有配置在resources下.</h4>
<ul>
<li>数据库: MySQL</li>
<li>服务器、框架：springboot</li>
<li>MVC： SpringMVC</li>
<li>数据库连接池： c3p0</li>
<li>数据库ORM： mybatis</li>
<li>前端页面： JSP</li>
<li>日志： log4j</li>
</ul>
<h4>后端所有文件在src main目录下的java.com.demo.clockin中</h4>
<ul>
<li>common：项目的通用类、实体、枚举、全局异常处理类、全局静态变量加载等</li>
<li>controller：项目业务控制器</li>
<li>dao：mybatis映射类</li>
<li>domain：orm对象类（使用mybatis-config里的驼峰）</li>
<li>service：业务逻辑层，严格按照接口，实现的形式</li>
</ul>
<p>其中controller中：</p>
<ul>
<li>action：页面跳转控制器，用于跳转前段请求跳转下一个页面</li>
<li>api：接口数据返回转控制器，用于前段请求返回json格式值</li>
<li>conf：配置数据库连接池以及事务aop等配置</li>
<li>filter：有登录拦截的fitler</li>
<li>listener：监听器</li> 
</ul>
<h4>前端所有文件在webapp文件夹下</h4>
<ul>
<li>jsp: 通用的jsp</li>
<li>rsource: 常用的插件</li>
<li>js　项目所需js 存放处</li>
<li>WEB-INF 项目前端页面JSP及web.xml</li>
</ul>
</article>
</br>
</br>
建议使用谷歌浏览器访问,部署时请修改application-dev.yml里的数据库连接以及你服务服务链接的ip（192.168.2.253）
</br>
登录账号：admin</br>
登录密码：12345678
