export class ClanMemberDonationsModel {
    tag: string;
    name: string;
    clanTag: string;
    memberSince: string;
    leftClan: string;
    donatedFromJoinDay: number;
    receivedFromJoinDay: number;
    averageWeeklyDonations: number;
    weekDonationsSoFar: number;
    weekDonationsReceivedSoFar: number;
    timesRejoined: number;
    remarks: string;
    donatedReceivedFromJoinDayRatio: number;
    donatedReceivedSoFarRatio: number;
}
