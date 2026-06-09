<script lang="ts">
    import { onMount } from "svelte";
    import { getSteamUser, type SteamUser } from "../../api/services/SteamAPI/GetSteamUser";

    let user = $state<SteamUser | null>(null);
    let loading = $state(true);

    const steamId = "76561199095017642";

    onMount(async () => {
        try {
            user = await getSteamUser(steamId);
        } finally {
            loading = false;
        }
    });

    function statusLabel(state: number): { text: string; color: string } {
        if (state === 1) return { text: "Online", color: "text-green-400 bg-green-400/10" };
        if (state === 3) return { text: "Away", color: "text-yellow-400 bg-yellow-400/10" };
        return { text: "Offline", color: "text-white/30 bg-white/5" };
    }

    function formatDate(unix: number) {
        return new Date(unix * 1000).toLocaleDateString("en-GB", {
            day: "numeric", month: "short", year: "numeric"
        });
    }
</script>

{#if loading}
	<div class="w-full max-w-xl mx-auto space-y-3 px-6">
		<div class="h-24 rounded-2xl bg-[rgba(242,237,231,0.04)] animate-pulse" />
		<div class="h-16 rounded-2xl bg-[rgba(242,237,231,0.04)] animate-pulse" />
	</div>

{:else if user}
	{@const status = statusLabel(user.personastate)}

	<div class="w-full max-w-xl mx-auto rounded-2xl border border-[rgba(242,237,231,0.07)] bg-[rgba(18,16,15,0.9)] backdrop-blur-[16px] overflow-hidden">

		<!-- HEADER -->
		<div class="flex items-center gap-4 p-6 border-b border-[rgba(242,237,231,0.07)]">

			<img
					src={user.avatarfull}
					alt="avatar"
					class="w-16 h-16 rounded-xl border border-[rgba(242,237,231,0.1)]"
			/>

			<div class="flex-1 min-w-0">
				<p class="text-white font-bold text-lg truncate">
					{user.personaname}
				</p>

				<p class="text-xs text-[rgba(242,237,231,0.4)] mt-0.5">
					Steam Community Profile
				</p>

				<a
						href={user.profileurl}
						target="_blank"
						class="text-[#66c0f4] text-sm hover:underline mt-1 inline-block"
				>
					View profile ↗
				</a>
			</div>

			<span class="text-xs font-bold px-3 py-1.5 rounded-full {status.color}">
				{status.text}
			</span>
		</div>

		<!-- QUICK STATS BAR -->
		<div class="grid grid-cols-3 gap-2 p-4 border-b border-[rgba(242,237,231,0.07)]">

			<div class="text-center">
				<p class="text-[10px] text-[rgba(242,237,231,0.4)] tracking-[2px]">STATUS</p>
				<p class="text-sm text-white mt-1">{status.text}</p>
			</div>

			<div class="text-center">
				<p class="text-[10px] text-[rgba(242,237,231,0.4)] tracking-[2px]">PROFILE</p>
				<p class="text-sm text-white mt-1">
					{user.communityvisibilitystate === 3 ? "Public" : "Private"}
				</p>
			</div>

			<div class="text-center">
				<p class="text-[10px] text-[rgba(242,237,231,0.4)] tracking-[2px]">STATE</p>
				<p class="text-sm text-white mt-1">
					{user.personastate === 1 ? "Active" : "Inactive"}
				</p>
			</div>

		</div>

		<!-- INFO GRID -->
		<div class="grid grid-cols-2 gap-3 p-6">

			<div class="rounded-xl border border-[rgba(242,237,231,0.07)] bg-[rgba(242,237,231,0.03)] px-4 py-3">
				<p class="text-[10px] tracking-[2px] text-[rgba(242,237,231,0.4)] mb-1">
					STEAM ID
				</p>
				<p class="text-sm text-white font-medium truncate">
					{user.steamid}
				</p>
			</div>

			<div class="rounded-xl border border-[rgba(242,237,231,0.07)] bg-[rgba(242,237,231,0.03)] px-4 py-3">
				<p class="text-[10px] tracking-[2px] text-[rgba(242,237,231,0.4)] mb-1">
					LAST ONLINE
				</p>
				<p class="text-sm text-white font-medium">
					{formatDate(user.lastlogoff)}
				</p>
			</div>

			<div class="rounded-xl border border-[rgba(242,237,231,0.07)] bg-[rgba(242,237,231,0.03)] px-4 py-3">
				<p class="text-[10px] tracking-[2px] text-[rgba(242,237,231,0.4)] mb-1">
					ACCOUNT CREATED
				</p>
				<p class="text-sm text-white font-medium">
					{formatDate(user.timecreated)}
				</p>
			</div>

			<div class="rounded-xl border border-[rgba(242,237,231,0.07)] bg-[rgba(242,237,231,0.03)] px-4 py-3">
				<p class="text-[10px] tracking-[2px] text-[rgba(242,237,231,0.4)] mb-1">
					VISIBILITY
				</p>
				<p class="text-sm text-white font-medium">
					{user.communityvisibilitystate === 3 ? "Public Profile" : "Private Profile"}
				</p>
			</div>

		</div>

	</div>
{/if}