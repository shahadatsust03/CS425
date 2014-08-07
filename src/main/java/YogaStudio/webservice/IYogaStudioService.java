/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.webservice;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Shahadat
 */
public interface IYogaStudioService {
    public ModelAndView getFavorite();
    public ModelAndView getRecent();
}
