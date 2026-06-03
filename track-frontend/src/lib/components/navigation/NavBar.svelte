<script>
	// Your Svelte logic can go here
</script>

<nav class="sticky top-0 z-[200] flex items-center justify-between border-b border-[rgba(242,237,231,0.07)] bg-[rgba(18,16,15,0.9)] px-[72px] py-[22px] backdrop-blur-[16px]">
	<ul>
		<li class="flex flex-row items-center gap-[44px]">
			<h1 class="text-2xl font-bold tracking-tight text-white">
				<span class="italic text-[#66c0f4]">TIME</span><span>Tracker</span>
			</h1>

			<p class="group relative cursor-pointer text-[11px] font-medium tracking-[2.5px] text-[rgba(242,237,231,0.45)] transition-colors duration-200 hover:text-[rgba(242,237,231,1)]">
				FORUMS
				<span class="absolute -bottom-0.5 left-0 h-[1px] w-0 bg-[#66c0f4] transition-all duration-[250ms] ease-in-out group-hover:w-full"></span>
			</p>

			<p class="group relative cursor-pointer text-[11px] font-medium tracking-[2.5px] text-[rgba(242,237,231,0.45)] transition-colors duration-200 hover:text-[rgba(242,237,231,1)]">
				GUIDE
				<span class="absolute -bottom-0.5 left-0 h-[1px] w-0 bg-[#66c0f4] transition-all duration-[250ms] ease-in-out group-hover:w-full"></span>
			</p>

			<p class="group relative cursor-pointer text-[11px] font-medium tracking-[2.5px] text-[rgba(242,237,231,0.45)] transition-colors duration-200 hover:text-[rgba(242,237,231,1)]">
				GAMES
				<span class="absolute -bottom-0.5 left-0 h-[1px] w-0 bg-[#66c0f4] transition-all duration-[250ms] ease-in-out group-hover:w-full"></span>
			</p>
		</li>
	</ul>

	<button class="rounded-full bg-[rgba(242,237,231,1)] px-[28px] py-[10px] text-[13px] font-bold text-[#12100f] transition-all duration-200 hover:scale-[1.04] hover:bg-[#66c0f4]">
		Sign In
	</button>
</nav>