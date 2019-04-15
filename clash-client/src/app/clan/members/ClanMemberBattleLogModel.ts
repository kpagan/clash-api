import { ArenaModel } from 'src/app/api/model/ArenaModel';

export class ClanMemberBattleLogModel {
    tag: string;
    name: string;
    expLevel: number;
    trophies: number;
    arena: ArenaModel;
    role: string;
    lastBattle: string;
}
