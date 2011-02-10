package cc.kune.core.client.state;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class AccessRightsChangedEvent extends GwtEvent<AccessRightsChangedEvent.AccessRightsChangedHandler> { 

  public interface HasAccessRightsChangedHandlers extends HasHandlers {
    HandlerRegistration addAccessRightsChangedHandler(AccessRightsChangedHandler handler);
  }

  public interface AccessRightsChangedHandler extends EventHandler {
    public void onAccessRightsChanged(AccessRightsChangedEvent event);
  }

  private static final Type<AccessRightsChangedHandler> TYPE = new Type<AccessRightsChangedHandler>();

  public static void fire(HasHandlers source, cc.kune.core.shared.domain.utils.AccessRights previousRights, cc.kune.core.shared.domain.utils.AccessRights currentRights) {
    source.fireEvent(new AccessRightsChangedEvent(previousRights, currentRights));
  }

  public static Type<AccessRightsChangedHandler> getType() {
    return TYPE;
  }

  cc.kune.core.shared.domain.utils.AccessRights previousRights;
  cc.kune.core.shared.domain.utils.AccessRights currentRights;

  public AccessRightsChangedEvent(cc.kune.core.shared.domain.utils.AccessRights previousRights, cc.kune.core.shared.domain.utils.AccessRights currentRights) {
    this.previousRights = previousRights;
    this.currentRights = currentRights;
  }

  protected AccessRightsChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<AccessRightsChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public cc.kune.core.shared.domain.utils.AccessRights getPreviousRights() {
    return previousRights;
  }

  public cc.kune.core.shared.domain.utils.AccessRights getCurrentRights() {
    return currentRights;
  }

  @Override
  protected void dispatch(AccessRightsChangedHandler handler) {
    handler.onAccessRightsChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    AccessRightsChangedEvent other = (AccessRightsChangedEvent) obj;
    if (previousRights == null) {
      if (other.previousRights != null)
        return false;
    } else if (!previousRights.equals(other.previousRights))
      return false;
    if (currentRights == null) {
      if (other.currentRights != null)
        return false;
    } else if (!currentRights.equals(other.currentRights))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (previousRights == null ? 1 : previousRights.hashCode());
    hashCode = (hashCode * 37) + (currentRights == null ? 1 : currentRights.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "AccessRightsChangedEvent["
                 + previousRights
                 + ","
                 + currentRights
    + "]";
  }
}
