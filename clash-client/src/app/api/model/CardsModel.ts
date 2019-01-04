import { IconUrlModel } from './IconUrlModel';

export class CardsModel {
    name: string;
    level: number;
    maxLevel: number;
    count: number;
    iconUrls: IconUrlModel;
    correctLevel?: number;
    maxCardLevel?: number;
}
