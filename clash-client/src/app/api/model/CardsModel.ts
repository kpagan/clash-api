import { IconUrlModel } from './IconUrlModel';
import { BasicCardModel } from './BasicCardModel';

export class CardsModel extends BasicCardModel {
    level: number;
    count: number;
    correctLevel?: number;
    maxCardLevel?: number;
}
