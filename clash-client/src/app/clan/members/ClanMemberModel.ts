import { ArenaModel } from 'src/app/api/model/ArenaModel';

export class ClanMemberModel {
    tag: string;
    name: string;
    expLevel: number;
    trophies: number;
    arena: ArenaModel;
    role: string;
    clanRank: number;
    previousClanRank: number;
    donations: number;
    donationsReceived: number;
    clanChestPoints: number;
}
