1. pip install supervisor
   => 根据 whereis python3 查到python安装路径，默认supervisor执行路径再该路径下
   => ./echo_supervisord_conf > supervisored.conf
   
   => 生成supervisord.conf配置文件
   mkdir -p /etc/supervisor/conf.d

   => cp supervisored.conf /etc/supervisor/


2. 修改配置文件：
    => 1. 注意linux系统可能没有/temp/目录建议/temp/*.sock, /temp/*.log, 含有temp的地方修改一下比如：  /var/run/*.sock   /var/log/*.log
    => 2. 末尾，去掉一下"," 并修改:
                            [include]
                            files = conf.d/*.ini
 
3. 在/etc/supervisord/conf.d/目录下新建对应的ini文件:
    + 注释版
            #项目名
            [program:blog]
            #脚本目录
            directory=/opt/bin
            #脚本执行命令
            command=/usr/bin/python /opt/bin/test.py

            #supervisor启动的时候是否随着同时启动，默认True
            autostart=true
            #当程序exit的时候，这个program不会自动重启,默认unexpected，设置子进程挂掉后自动重启的情况，有三个选项，false,unexpected和true。如果为false的时候，无论什么情况下，都不会被重新启动，如果为unexpected，只有当进程的退出码不在下面的exitcodes里面定义的
            autorestart=false
            #这个选项是子进程启动多少秒之后，此时状态如果是running，则我们认为启动成功了。默认值为1
            startsecs=1
            #脚本运行的用户身份 
            user = test

            #日志输出 
            stderr_logfile=/var/log/blog_stderr.log 
            stdout_logfile=/var/log/blog_stdout.log 
            #把stderr重定向到stdout，默认 false
            redirect_stderr = true
            #stdout日志文件大小，默认 50MB
            stdout_logfile_maxbytes = 20MB
            #stdout日志文件备份数
            stdout_logfile_backups = 20
    + 精简版：
            [program:test] 
            directory=/opt/bin 
            command=/opt/bin/test
            autostart=true 
            autorestart=false 
            stderr_logfile=/tmp/test_stderr.log 
            stdout_logfile=/tmp/test_stdout.log 
            #user = test  
4. 启动supervisord  /usr/local/bin/supervisord  -c /etc/supervisod/supervisord.conf
5. 常用命令：
      + supervisorctl status        //查看所有进程的状态
        supervisorctl stop es       //停止es
        supervisorctl start es      //启动es
        supervisorctl restart       //重启es
        supervisorctl update        //配置文件修改后使用该命令加载新的配置
        supervisorctl reload        //重新启动配置中的所有程序
6. 设置多进程：
    process_name=%(program_name)s_%(process_num)02d              ;多进程名称肯定不能相同，匹配多个
    numprocs=4  
    + stopasgroup=true。这一配置项的作用是：如果supervisord管理的进程px又产生了若干子进程，使用supervisorctl停止px进程，停止信号会传播给px产生的所有子进程，确保子进程也一起停止。这一配置项对希望停止所有进程的需求是非常有用的。
    +autostart=true。这一配置项的作用是：当启动supervisord的时候会将该配置项设置为true的所有进程自动启动。
    一份配置文件至少需要一个 [program:x]部分的配置，来告诉 supervisord 需要管理那个进程。[program:x]语法中的 x 表示 program name，会在客户端（supervisorctl 或 web 界面）显示，在 supervisorctl 中通过这个值来对程序进行 start、restart、stop 等操作。
