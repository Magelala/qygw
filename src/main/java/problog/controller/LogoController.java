package problog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import problog.entity.logo.Logo;
import problog.entity.response.ResResult;
import problog.service.LogoService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: shengjun
 * @Date: 2019/10/26 22:22
 */
@Controller
@RequestMapping("/logo")
public class LogoController {

    @Autowired
    private LogoService logoService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<Logo> show(){
        ResResult<Logo> resResult = new ResResult<>();
        Logo logo = logoService.getById(1);
        resResult.setData(logo);
        resResult.setCode(0);
        resResult.setCount(1);
        return resResult;
    }


    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<Logo> update(@RequestBody Logo logo){
        ResResult<Logo> resResult = new ResResult<>();
        String path = (String)request.getSession().getAttribute("path");
        logo.setSrc(path);
        request.getSession().setAttribute("path",null);
        request.getSession().setAttribute("src",path);
        logo.setAuthorId(1);
        logo.setAuthorName("admin");
        logoService.update(logo);
        resResult.setCode(200);
        resResult.setCount(1);
        resResult.setData(logo);
        resResult.setMsg("修改logo成功");
        return resResult;
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        Logo logo = logoService.getById(1);
        model.addAttribute("logo",logo);
        return "logo/logo";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(@RequestParam("id") Integer id, Model model){
        model.addAttribute("logo",logoService.getById(id));
        return "logo/edit";
    }

}
