package cn.itcast.web.controller;

import cn.itcast.domain.Databackup;
import cn.itcast.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DbServlet extends HttpServlet {
    BussinessServiceImpl service = new BussinessServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        //备份
        if ("backup".equalsIgnoreCase(method)) {
            backup(request, response);
        }
        //恢复
        if ("recover".equalsIgnoreCase(method)) {
            recover(request, response);
        }
        //列出数据库列表
        if ("listRecovery".equalsIgnoreCase(method)) {
            listRecovery(request, response);
        }
    }

    //显示备份数据条目
    private void listRecovery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //1,从数据库获得数据
        List list = service.getAllDatabackup();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/manag/listBackup.jsp").forward(request,response);

    }

    //执行恢复
    private void recover(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Databackup databackup = service.findDatabackupById(id);
            String filename = databackup.getFilename();  //获得路径和文件名
            File file = new File(filename);
            String command =
                    "cmd /c mysql -uroot -proot day23_bookstore <" + filename;
            Runtime.getRuntime().exec(command);
            request.setAttribute("message","恢复数据成功!");
            } catch (Exception e) {
            request.setAttribute("message","恢复数据失败!!");
            e.getStackTrace();
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    //执行备份
    private void backup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String savapath = this.getServletContext().getRealPath("/dbak"); //保存路径
            checkSavePath(savapath);
            String filename = System.currentTimeMillis() + ".sql";          //保存文件名称

            //1,做备份操作
            String command = "cmd /c mysqldump -uroot -proot day23_bookstore >" + savapath + "\\" + filename;
            Runtime.getRuntime().exec(command); //执行cmd命令

            //2,把备份信息封装到javabean,存到数据库
            String description = request.getParameter("description");
            Databackup databackup = new Databackup();
            databackup.setId(UUID.randomUUID().toString());
            databackup.setBaktime(new Date());
            databackup.setFilename(savapath + File.separator + filename);
            databackup.setDescription(description);
            service.addDatabackup(databackup);

            request.setAttribute("message", "备份成功!");
        } catch (Exception e) {
            e.getStackTrace();
            request.setAttribute("message", "备份失败!");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);

    }

    private void checkSavePath(String savapath) {
        File file = new File(savapath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
