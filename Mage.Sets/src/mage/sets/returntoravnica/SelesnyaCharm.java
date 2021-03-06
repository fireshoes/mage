/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.returntoravnica;

import java.util.UUID;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.abilities.Mode;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.continious.BoostTargetEffect;
import mage.abilities.effects.common.continious.GainAbilityTargetEffect;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.constants.Duration;
import mage.filter.Filter;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.PowerPredicate;
import mage.game.permanent.token.KnightToken;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author LevelX2
 */
public class SelesnyaCharm extends CardImpl {

    static private final FilterCreaturePermanent filter = new FilterCreaturePermanent("creature with power 5 or greater");
    
    static {
        filter.add(new PowerPredicate(Filter.ComparisonType.GreaterThan, 4));
    }

    public SelesnyaCharm(UUID ownerId) {
        super(ownerId, 194, "Selesnya Charm", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{G}{W}");
        this.expansionSetCode = "RTR";

        this.color.setGreen(true);
        this.color.setWhite(true);
       
        // Choose one — Target creature gets +2/+2 and gains trample until end of turn;
        Effect effect = new BoostTargetEffect(2,2, Duration.EndOfTurn);
        effect.setText("Target creature gets +2/+2");
        this.getSpellAbility().addEffect(effect);
        effect = new GainAbilityTargetEffect(TrampleAbility.getInstance(), Duration.EndOfTurn);
        effect.setText("and gains trample until end of turn");
        this.getSpellAbility().addEffect(effect);
        this.getSpellAbility().getTargets().add(new TargetCreaturePermanent());

        // or exile target creature with power 5 or greater;
        Mode mode = new Mode();
        mode.getEffects().add(new ExileTargetEffect());
        mode.getTargets().add(new TargetCreaturePermanent(filter));
        this.getSpellAbility().addMode(mode);

        // or put a 2/2 white Knight creature token with vigilance onto the battlefield.
        mode = new Mode();
        mode.getEffects().add(new CreateTokenEffect(new KnightToken()));
        this.getSpellAbility().addMode(mode);
    }

    public SelesnyaCharm(final SelesnyaCharm card) {
        super(card);
    }

    @Override
    public SelesnyaCharm copy() {
        return new SelesnyaCharm(this);
    }
}
