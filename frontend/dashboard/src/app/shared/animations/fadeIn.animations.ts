// import the required animation functions from the angular animations module
import { animate, style, transition, trigger } from '@angular/animations';

export function fadeInAnimation() {
  // trigger name for attaching this animation to an element using the [@triggerName] syntax
  return trigger('fadeInAnimation', [
    // route 'enter' transition
    transition(':enter', [
      // css styles at start of transition
      style({ opacity: 0 }),

      // animation and styles at end of transition
      animate('.3s', style({ opacity: 1 })),
    ]),
  ]);
}
