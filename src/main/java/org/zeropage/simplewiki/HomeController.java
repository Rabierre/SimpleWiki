package org.zeropage.simplewiki;

import java.util.Locale;

import org.pegdown.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zeropage.simplewiki.crud.PageRepository;
import org.zeropage.simplewiki.crud.UserRepository;
import org.zeropage.simplewiki.model.Page;
import org.zeropage.simplewiki.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
    @Autowired
    PageRepository pageRepository;
    @Autowired
    UserRepository userRepository;
    private PasswordEncoder encoder = new ShaPasswordEncoder(256);
    //private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    //임시로 public
    @Autowired
    public Parser parser;
    /**
     * Simply selects the home view to render by returning its name.
     */
   
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "redirect:/wiki/FrontPage";
    }


    @RequestMapping(value = "/wiki/{pageTitle}", method = RequestMethod.GET)
    public String home(@PathVariable("pageTitle") String pageTitle, Locale locale, Model model) {
        Page page = pageRepository.get(pageTitle);
        if(page == null){
            model.addAttribute("title", pageTitle);
            return "create";
        }else{
        	model.addAttribute("page", page);
            return "home";
        }
    }
    
    @RequestMapping(value = "/wiki/{pageTitle}", method = RequestMethod.GET, params="action=history")
    public String home(@RequestParam("action") String action, @PathVariable("pageTitle") String pageTitle, Locale locale, Model model) {
		Page page = pageRepository.get(pageTitle);
		model.addAttribute("page", page);
    	return "history";
    }
    
    @Transactional
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@RequestParam("title") String title, @RequestParam("contents") String contents, Model model) {
        Page page = pageRepository.get(title);

        if (page != null) {
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String name;
	        if (principal instanceof UserDetails) {
	            name = ((UserDetails) principal).getUsername();
	        } else {
	            name = principal.toString();
	        }
	        page.edit(contents, userRepository.get(name));
	        
	        pageRepository.update(page);
        }
        return "redirect:/";
    }
    
    @Transactional
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam("title") String title, Model model){
    	Page page = new Page(title);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name;
        if (principal instanceof UserDetails) {
            name = ((UserDetails) principal).getUsername();
           	page.edit("This is New Page.", userRepository.get(name));
            pageRepository.update(page);
            model.addAttribute("page", page);
            
            return "redirect:/wiki/"+title;
        }
        
        return "home";    	
    }
    
    @Transactional
    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String signout() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id;
        
        if (principal instanceof UserDetails) {
            id = ((UserDetails) principal).getUsername();
        } else {
            id = principal.toString();
        }
        
        User user = userRepository.get(id);
        user.removeInfo();
    	userRepository.update(user);
    	
    	return "redirect:/logout";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("title") String title) {
        pageRepository.delete(pageRepository.get(title));

        return "redirect:/";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@RequestParam("id") String id, @RequestParam("password") String password, @RequestParam("email") String email) {
        userRepository.update(new User(id, encoder.encodePassword(password, id), email));

        return "redirect:/";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }
}
