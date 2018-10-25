import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by no on 2018/10/23.
 */
public class MenuTest extends CmfzTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void queryAll(){
        List<Menu> menus = menuService.queryAll();
        System.out.println(menus);

    }
}
