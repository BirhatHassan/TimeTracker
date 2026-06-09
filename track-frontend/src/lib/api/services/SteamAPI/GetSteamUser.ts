import { request } from "../../HTTPClient";

export interface SteamUser {
    steamid: string;
    communityvisibilitystate: number;
    profilestate: number;
    personaname: string;
    profileurl: string;
    avatar: string;
    avatarmedium: string;
    avatarfull: string;
    avatarhash: string;
    lastlogoff: number;
    personastate: number;
    primaryclanid: string;
    timecreated: number;
    personastateflags: number;
}

export async function getSteamUser(steamId: string): Promise<SteamUser> {
    return await request(`/api/steam/user/${steamId}`, {
        method: "GET",
    });
}