import { Directive, ElementRef, Renderer2 } from "@angular/core";

@Directive({ selector: '[stopccp]' })

export class StopCcpDirective {
    constructor(el: ElementRef, renderer: Renderer2) {
        var events = 'cut copy paste';
        events.split(' ').forEach(e =>
            renderer.listen(el.nativeElement, e, (event) => {
                event.preventDefault();
            })
        );
    }
}