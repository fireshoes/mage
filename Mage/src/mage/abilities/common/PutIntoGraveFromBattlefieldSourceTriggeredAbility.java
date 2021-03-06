package mage.abilities.common;

import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.effects.Effect;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;

/**
 * @author nantuko, loki
 */
public class PutIntoGraveFromBattlefieldSourceTriggeredAbility extends TriggeredAbilityImpl {

    public PutIntoGraveFromBattlefieldSourceTriggeredAbility(Effect effect) {
        this(effect, false);
    }

    public PutIntoGraveFromBattlefieldSourceTriggeredAbility(Effect effect, boolean optional) {
        super(Zone.ALL, effect, optional);
    }

    PutIntoGraveFromBattlefieldSourceTriggeredAbility(PutIntoGraveFromBattlefieldSourceTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public PutIntoGraveFromBattlefieldSourceTriggeredAbility copy() {
        return new PutIntoGraveFromBattlefieldSourceTriggeredAbility(this);
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.ZONE_CHANGE) {

            ZoneChangeEvent zEvent = (ZoneChangeEvent) event;
            Permanent permanent = zEvent.getTarget();

            if (permanent != null &&
                    zEvent.getToZone() == Zone.GRAVEYARD &&
                    zEvent.getFromZone() == Zone.BATTLEFIELD &&
                    permanent.getId().equals(this.getSourceId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRule() {
        return "When {this} is put into a graveyard from the battlefield, " + super.getRule();
    }
}
