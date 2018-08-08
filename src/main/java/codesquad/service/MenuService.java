package codesquad.service;

import codesquad.domain.Menu;
import codesquad.domain.MenuContext;
import codesquad.repository.MenuRepository;
import codesquad.support.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public Menu getRootMenu(){
        return menuRepository.findById(MenuContext.MAIN_CATEGORY.getId()).orElseThrow(() -> new NotExistException("최상단 메뉴 존재하지 않음"));
    }

    public Menu getSpecificMenu(long id){
        return menuRepository.findById(id).orElseThrow(() -> new NotExistException("없는 메뉴 입니다."));
    }

    @Transactional
    public void deleteMenu(long menuId){
        Menu deleteTarget = getSpecificMenu(menuId);
        if(deleteTarget.hasChild()) throw new RuntimeException("자식있어서 실패");
        if(!deleteTarget.hasParent()) throw new RuntimeException("루트 노드 입니다.");
        deleteTarget.delete();
    }

    public void registMenu(long parentId, String childMenuName){
        Menu parent = getSpecificMenu(parentId);
        Menu childMenu = new Menu(childMenuName, parent);
        parent.registChild(childMenu);
        menuRepository.save(parent);
    }
}
