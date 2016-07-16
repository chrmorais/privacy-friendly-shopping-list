package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.deletelists.listeners;

import android.view.View;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.context.AbstractInstanceFactory;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.context.InstanceFactory;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.ShoppingListService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.domain.ListDto;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.deletelists.DeleteListsCache;

import java.util.List;

/**
 * Description:
 * Author: Grebiel Jose Ifill Brito
 * Created: 16.07.16 creation date
 */
public class DeleteOnClickListener implements View.OnClickListener
{
    private ShoppingListService shoppingListService;
    private DeleteListsCache cache;

    public DeleteOnClickListener(DeleteListsCache cache)
    {
        this.cache = cache;
        AbstractInstanceFactory instanceFactory = new InstanceFactory(cache.getActivity().getApplicationContext());
        this.shoppingListService = (ShoppingListService) instanceFactory.createInstance(ShoppingListService.class);
    }

    @Override
    public void onClick(View v)
    {
        List<ListDto> shoppingList = cache.getDeleteListsAdapter().getShoppingList();
        for ( ListDto dto : shoppingList )
        {
            if ( dto.isSelected() )
            {
                shoppingListService.deleteById(dto.getId());
            }
        }
        updateListView();
    }

    public void updateListView()
    {
        cache.getDeleteListsAdapter().setShoppingList(shoppingListService.getAllListDtos());
        cache.getDeleteListsAdapter().notifyDataSetChanged();
    }
}