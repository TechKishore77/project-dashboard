import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';

export function flyInOut() {
  return trigger('flyInOut', [
    state('in', style({ transform: 'translateX(0)' })),
    transition('void => *', [
      style({ transform: 'translateX(100%)' }),
      // animate(100)
      animate(
        '.4s .2s ease-in-out',
        style({ opacity: '1', transform: 'translateY(0)' })
      ),
    ]),
    transition('* => void', [
      // animate(100, style({ transform: 'translateX(100%)' }))
      animate(
        '.3s ease-in-out',
        style({ opacity: '0', transform: 'translateX(-200px)' })
      ),
    ]),
  ]);
}
