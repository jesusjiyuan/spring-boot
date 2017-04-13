package springboot.controller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import springboot.Uri;
import springboot.entity.Salary;
import springboot.entity.Userinfo;
import springboot.entity.Users;
import springboot.repository.InformResp;
import springboot.repository.NewsResp;
import springboot.repository.SalaryResp;
import springboot.repository.UserinfoResp;
import springboot.session.MySessionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 16:20
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
@RestController
@RequestMapping("/judement")
public class BodyControll {

    @Autowired
    private UserinfoResp userinfoResp;
    @Autowired
    private InformResp informResp;
    @Autowired
    private NewsResp newsResp;
    @Autowired
    private SalaryResp salary;
    @Autowired
    private Uri uri;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> hasuser(@RequestBody Userinfo a, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        if (userinfoResp.findAccount(a.getNumber()) == null || !a.getPassword().equals(userinfoResp.findAccount(a.getNumber()))) {
            map.put("message", "密码错误");
            return map;
        }
        Timestamp d = new Timestamp(System.currentTimeMillis());
        String time = d.toString();
        userinfoResp.updateTime(time, a.getNumber());
        String name = userinfoResp.findName(a.getNumber());
        request.getSession().setAttribute("name", name);
        request.getSession().setAttribute("id", a.getNumber());
        MySessionContext.AddSession(request.getSession());
        map.put("message", "true");
        return map;
    }

    @RequestMapping(value = "/counts", method = RequestMethod.POST)
    public List<String> counts() {
        List<String> list = Users.name();
        List<String> listNew = new ArrayList<>();
        for (String number : list) {
            listNew.add(userinfoResp.findName(number));
        }
        return listNew;
    }

    @RequestMapping(value = "/where", method = RequestMethod.POST)
    public List<String> where(@RequestBody Map map, @CookieValue("JSESSIONID") String fooCookie) {
        HttpSession session = MySessionContext.getSession(fooCookie);
        String id = (String) session.getAttribute("id");
        List<String> list = new ArrayList<>();
        if (map.get("from").equals(userinfoResp.findDepart(id))) {
            list.add("true");
            return list;
        }
        list.add("false");
        return list;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void send(@RequestBody Map map) {
        informResp.updateMessage((String) map.get("message"), 1);
        userinfoResp.flush();
    }

    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    public String commit(@RequestBody Map<String, String> map) {
        try {
            String content = map.get("content").replace("\"", "\'");
            newsResp.updateNews(map.get("title"), content, Integer.parseInt(map.get("id")));
        } catch (NumberFormatException e) {
            return "false";
        }
        return "true";
    }

    @RequestMapping(value = "/depart", method = RequestMethod.POST)
    public void depart(@CookieValue("JSESSIONID") String fooCookie) {
        MySessionContext.DelSession(fooCookie);
    }

    @RequestMapping(value = "/addEmp", method = RequestMethod.POST)
    public void addEmp(@RequestBody Userinfo userEntity) {
        userinfoResp.saveAndFlush(userEntity);
    }

    @RequestMapping(value = "/insert.json", method = RequestMethod.POST)
    public void insert(@RequestParam("file") CommonsMultipartFile file) {
        if (!file.isEmpty()) {
            String url = uri.INSERTEXCEL + file.getOriginalFilename();
            XSSFWorkbook workbook;
            Salary salaryEntity = new Salary();
            try {
                file.transferTo(new File(url));
                workbook = new XSSFWorkbook(new FileInputStream(url));
                XSSFSheet sheet = workbook.getSheet("Sheet1");
                int rows = sheet.getPhysicalNumberOfRows();
                for (int i = 1; i < rows; ++i) {
                    XSSFRow row = sheet.getRow(i);
                    if (row != null) {
                        int cells = row.getPhysicalNumberOfCells();
                        String value = "";
                        for (int j = 0; j < cells; ++j) {
                            XSSFCell cell = row.getCell(j);
                            if (cell != null) {
                                switch (cell.getCellType()) {
                                    case HSSFCell.CELL_TYPE_FORMULA://2
                                        break;
                                    case HSSFCell.CELL_TYPE_NUMERIC://0
                                        value += (int) cell.getNumericCellValue() + ",";
                                        break;
                                    case HSSFCell.CELL_TYPE_STRING://1
                                        value += cell.getStringCellValue() + ",";
                                        break;
                                    default:
                                        value += 0;
                                }
                            }
                        }
                        String[] val = value.split(",");
                        long a = Long.parseLong(val[0]);
                        salaryEntity.setId(a);
                        salaryEntity.setNum(val[1]);
                        salaryEntity.setSalary(val[2]);
                        salary.saveAndFlush(salaryEntity);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    public Map<String, String> edit(@RequestBody Map<String, String> map, HttpServletRequest request) {
        Map<String, String> r = new HashMap<>();
        final String nei = request.getRemoteAddr() + "：" + map.get("shebei") + "  " + map.get("banben") + "\n";
        try {
            FileWriter writer = new FileWriter(uri.EDITURI, true);
            writer.write(nei);
            writer.close();
        } catch (IOException e) {
            r.put("message", "false");
            return r;
        }
        r.put("message", "true");
        return r;
    }

    @RequestMapping(value = "/testUpload", method = RequestMethod.POST)
    public void testUpload(HttpServletRequest req,MultipartHttpServletRequest multiReq) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("F://test//src//file//upload.jpg"));
        FileInputStream fs = (FileInputStream) multiReq.getFile("file").getInputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fs.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();
    }
}
