package cn.itcast.utils;

import cn.itcast.domain.Book;
import cn.itcast.domain.Category;
import cn.itcast.service.impl.BussinessServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WebUtils {


    public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
        try {

            T bean = beanClass.newInstance();
            Map map = request.getParameterMap();
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Book upload(HttpServletRequest request,String savepath) {
        InputStream in;FileOutputStream out;Book book;

        File file=new File(savepath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            List<FileItem> list = upload.parseRequest(request);
             book= new Book();
            for (FileItem item : list) {
                if (item.isFormField()) {  //input输入项
                    String fieldName = item.getFieldName();
                    String fieldvalue = item.getString("UTF-8");
                    if ("category_id".equals(fieldName)) {
                        Category category = new BussinessServiceImpl().findCategoryById(fieldvalue);
                        book.setCategory(category);
                        continue;
                    }
                    BeanUtils.setProperty(book,fieldName,fieldvalue);
                    continue;
                }//上传的文件
                String filename=item.getName();
//                filename=new String(filename.getBytes("ISO8859-1"),"UTF-8");
                filename=filename.substring(filename.lastIndexOf('\\')+1);

                String savefilename= UUID.randomUUID().toString()+filename;
                in = item.getInputStream();
                out = new FileOutputStream(savepath + File.separator+savefilename);
                int len=0;
                byte[] buffer = new byte[1024];
                while ((len=in.read(buffer))>0) {
                    out.write(buffer,0,len);
                }
                item.delete();
                book.setImage(savefilename);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        book.setId(UUID.randomUUID().toString());
        return book;
    }

}	
