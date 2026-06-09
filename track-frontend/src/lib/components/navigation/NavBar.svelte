<script lang="ts">
    import { onMount } from "svelte";
    import { getMyInfo } from "../../api/services/UserServices/getMyInfo";
    import type { MyUserInfo } from "../../api/interface/MyUserInfo";

    let user = $state<MyUserInfo | null>(null);
    let loading = $state(true);
    let open = $state(false);

    let dropdownRef: HTMLDivElement;

    function toggleDropdown() {
        open = !open;
    }

    function closeDropdown() {
        open = false;
    }

    function handleClickOutside(node: HTMLElement) {
        const handle = (event: MouseEvent) => {
            if (node && !node.contains(event.target as Node)) {
                closeDropdown();
            }
        };

        document.addEventListener("click", handle);

        return {
            destroy() {
                document.removeEventListener("click", handle);
            }
        };
    }

    onMount(async () => {
        try {
            user = await getMyInfo();
        } catch {
            user = null;
        } finally {
            loading = false;
        }
    });

    function logout() {
        document.cookie = "jwt=; Max-Age=0; path=/";
        user = null;
        closeDropdown();
        window.location.href = "/login";
    }
</script>

<nav class="sticky top-0 z-[200] flex items-center justify-between border-b border-[rgba(242,237,231,0.07)] bg-[rgba(18,16,15,0.9)] px-[72px] py-[18px] backdrop-blur-[16px]">

	<!-- LEFT: Brand -->
	<div class="flex items-center gap-10">

		<h1 class="text-2xl font-bold tracking-tight text-white">
			<span class="italic text-[#66c0f4]">BackLog</span><span class="ml-0.5 opacity-90">DB</span>
		</h1>

		<!-- NAV BUTTONS -->
		<div class="flex items-center gap-2">

			<button class="rounded-md px-4 py-2 text-[11px] font-bold tracking-[2.2px] text-[rgba(242,237,231,0.45)] transition-all duration-200
				hover:bg-white/5 hover:text-white hover:scale-[1.03] active:scale-[0.98]">
				FORUMS
			</button>

			<button class="rounded-md px-4 py-2 text-[11px] font-bold tracking-[2.2px] text-[rgba(242,237,231,0.45)] transition-all duration-200
				hover:bg-white/5 hover:text-white hover:scale-[1.03] active:scale-[0.98]">
				GUIDES
			</button>

			<button class="rounded-md px-4 py-2 text-[11px] font-bold tracking-[2.2px] text-[rgba(242,237,231,0.45)] transition-all duration-200
				hover:bg-white/5 hover:text-white hover:scale-[1.03] active:scale-[0.98]">
				GAMES
			</button>

		</div>
	</div>

	<div class="flex items-center gap-4">

		{#if loading}
			<div class="text-[13px] text-white/40 animate-pulse">Loading...</div>

		{:else if user}

			<!-- USER DROPDOWN -->
			<div class="relative" use:handleClickOutside bind:this={dropdownRef}>

				<!-- Button -->
				<button
						on:click={toggleDropdown}
						class="flex items-center gap-2 rounded-full bg-white px-4 py-2 text-[13px] font-bold text-[#12100f] transition-all hover:scale-[1.04] hover:bg-[#66c0f4]"
				>
					<div class="h-6 w-6 rounded-full bg-black/10 flex items-center justify-center text-[11px]">
						{user.username.charAt(0).toUpperCase()}
					</div>

					{user.username}
				</button>

				<!-- Dropdown -->
				{#if open}
					<div class="absolute right-0 mt-2 w-44 rounded-lg border border-white/10 bg-[#1a1716] shadow-lg overflow-hidden">

						<a href="/account"
						   class="block px-4 py-3 text-sm text-white/70 hover:bg-white/5 hover:text-white">
							My Account
						</a>

						<button
								on:click={logout}
								class="w-full text-left px-4 py-3 text-sm text-red-400 hover:bg-white/5 hover:text-red-300"
						>
							Logout
						</button>

					</div>
				{/if}

			</div>

		{:else}
			<a href="/login"
			   class="rounded-full bg-white px-6 py-2 text-[13px] font-bold text-[#12100f] transition-all hover:scale-[1.04] hover:bg-[#66c0f4]">
				Sign In
			</a>
		{/if}

	</div>

</nav>