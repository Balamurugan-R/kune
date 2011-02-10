package cc.kune.core.client.sn.actions.registry;

import cc.kune.common.client.actions.ui.descrip.MenuItemDescriptor;
import cc.kune.core.client.sn.actions.AcceptJoinGroupAction;
import cc.kune.core.client.sn.actions.ChangeToAdminAction;
import cc.kune.core.client.sn.actions.ChangeToCollabAction;
import cc.kune.core.client.sn.actions.DenyJoinGroupAction;
import cc.kune.core.client.sn.actions.GotoGroupAction;
import cc.kune.core.client.sn.actions.GotoPersonAction;
import cc.kune.core.client.sn.actions.RemoveMemberAction;
import cc.kune.core.client.sn.actions.StartChatWithUserAction;
import cc.kune.core.client.sn.actions.conditions.IsAdministrableCondition;
import cc.kune.core.client.sn.actions.conditions.IsGroupCondition;
import cc.kune.core.client.sn.actions.conditions.IsPersonCondition;
import cc.kune.core.client.state.Session;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class SNPendingsMenuItemsRegistry extends AbstractSNMembersActionsRegistry {

    @Inject
    public SNPendingsMenuItemsRegistry(final Session session, final IsAdministrableCondition isAdministrableCondition,
            final IsPersonCondition isPersonCondition, final IsGroupCondition isGroupCondition,
            final ChangeToCollabAction changeToCollabAction, final ChangeToAdminAction changeToAdminAction,
            final RemoveMemberAction removeMemberAction, final AcceptJoinGroupAction acceptJoinGroupAction,
            final DenyJoinGroupAction denyJoinGroupAction, final StartChatWithUserAction startChatWithUserAction,
            final GotoGroupAction gotoGroupAction, final GotoPersonAction gotoPersonAction) {
        add(new Provider<MenuItemDescriptor>() {
            @Override
            public MenuItemDescriptor get() {
                final MenuItemDescriptor item = new MenuItemDescriptor(acceptJoinGroupAction);
                item.setAddCondition(isAdministrableCondition);
                return item;
            }
        });
        add(new Provider<MenuItemDescriptor>() {
            @Override
            public MenuItemDescriptor get() {
                final MenuItemDescriptor item = new MenuItemDescriptor(denyJoinGroupAction);
                item.setAddCondition(isAdministrableCondition);
                return item;
            }
        });
        add(new Provider<MenuItemDescriptor>() {
            @Override
            public MenuItemDescriptor get() {
                final MenuItemDescriptor item = new MenuItemDescriptor(gotoPersonAction);
                item.setAddCondition(isPersonCondition);
                return item;
            }
        });
        add(new Provider<MenuItemDescriptor>() {
            @Override
            public MenuItemDescriptor get() {
                final MenuItemDescriptor item = new MenuItemDescriptor(gotoGroupAction);
                item.setAddCondition(isGroupCondition);
                return item;
            }
        });
        add(new Provider<MenuItemDescriptor>() {
            @Override
            public MenuItemDescriptor get() {
                final MenuItemDescriptor item = new MenuItemDescriptor(startChatWithUserAction);
                item.setAddCondition(isPersonCondition);
                return item;
            }
        });
    }

}
