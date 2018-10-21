import { InjectionToken } from '@angular/core';
import * as $ from 'jquery';

export const JQUERY_TOKEN = new InjectionToken<JQueryStatic>('jquery');

export function jQueryFactory() {
    return $;
}

export const JQUERY_PROVIDER = { provide: JQUERY_TOKEN, useFactory: jQueryFactory };