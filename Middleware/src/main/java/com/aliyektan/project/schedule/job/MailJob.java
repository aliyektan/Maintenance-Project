package com.aliyektan.project.schedule.job;

import com.aliyektan.project.core.dao.CustomerDao;
import com.aliyektan.project.core.dao.ProcessDao;
import com.aliyektan.project.core.dao.ProductDao;
import com.aliyektan.project.core.entity.Process;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yektan on 18.12.2017.
 * Tüm işlemleri çekip tek tek gelecek bakım gününü kontrol edip
 * ekrana çıktıyı basıyoruz.
 */
public class MailJob implements Job {

    private ProcessDao processDao = new ProcessDao();
    private CustomerDao customerDao = new CustomerDao();
    private ProductDao productDao = new ProductDao();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Date now = new Date();
        Date nextProcessDate;
        String nowDate=dateFormat.format(now);
        String nextDate;
        Process tmp;

        try {
            List<Process> processList = processDao.findAll();

            for (Iterator<Process> i = processList.iterator(); i.hasNext();) {

                tmp = i.next();

                nextProcessDate = tmp.getNextProcess();
                nextDate=dateFormat.format(nextProcessDate);

                if (nowDate.equals(nextDate)) {
                    System.out.println("To:" + customerDao.getById(productDao.getById(tmp.getProductId()).getCustomerId()).getEmail());
                    System.out.println("Your product has come to the day of maintenance." + nowDate);
                }else{
                    System.out.print("No mail sent !");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
