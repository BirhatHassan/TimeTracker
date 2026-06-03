<section class="relative grid h-[calc(100vh-66px)] min-h-[700px] grid-cols-1 overflow-hidden lg:grid-cols-2 bg-[#0a0908]">

	<div class="relative z-10 flex flex-col justify-center border-r border-[rgba(242,237,231,0.07)] px-8 py-20 md:px-[72px]">
		<h1 class="animate-fadeUp text-white mb-8 text-[clamp(48px,5vw,76px)] font-extrabold leading-[0.92] tracking-tight">
			Keep <i class="not-italic text-[#66c0f4]">track</i> of your games anytime, everywhere.
		</h1>
		<p class="animate-fadeUp text-[rgba(242,237,231,0.45)] mb-12 max-w-[400px] text-base leading-relaxed">
			<strong class="font-medium text-white italic tracking-tight">
				<span class="italic text-[#66c0f4]">BackLog</span><span class="ml-0.5 opacity-90">DB</span>
			</strong> is the ultimate personal dashboard to catalog your gaming journey and manage your backlog efficiently.
		</p>
		<div class="animate-fadeUp flex flex-wrap items-center gap-3.5">
			<button class="rounded-full bg-[#66c0f4] px-[34px] py-3.5 text-sm font-bold text-[#12100f] transition-all duration-200 hover:scale-[1.05] hover:bg-white cursor-pointer">
				Get Started
			</button>
		</div>
	</div>

	<div class="relative flex h-full w-full items-center justify-center overflow-hidden bg-[#0a0908]">
		<div class="absolute inset-0 z-10 bg-gradient-to-b from-[#0a0908] via-transparent to-[#0a0908]"></div>

		<div class="animate-slowScroll grid grid-cols-3 gap-1">
			<div class="flex flex-col gap-1">
				<img src="https://upload.wikimedia.org/wikipedia/en/3/3d/Marvel%27s_Wolverine_cover_art.jpg?_=20251001210329" class="aspect-[2/3] w-56 rounded-lg object-cover" alt="Game">
				<img src="https://upload.wikimedia.org/wikipedia/en/4/4b/Hitman_3_Packart.jpg" class="aspect-[2/3] w-56 rounded-lg object-cover" alt="Game">
				<img src="https://upload.wikimedia.org/wikipedia/en/6/6d/Dying_Light_2_cover_art.jpg" class="aspect-[2/3] w-56 rounded-lg object-cover" alt="Game">
			</div>
			<div class="mt-32 flex flex-col gap-1">
				<img src="https://upload.wikimedia.org/wikipedia/en/1/15/Resident_Evil_Requiem_Cover_Art.jpg" class="aspect-[2/3] w-56 rounded-lg object-cover" alt="Game">
				<img src="https://upload.wikimedia.org/wikipedia/en/2/2b/007_First_Light_%282026%29_cover.jpg" class="aspect-[2/3] w-56 rounded-lg object-cover" alt="Game">
				<img src="https://upload.wikimedia.org/wikipedia/en/0/00/Batman_Arkham_City_Game_Cover.jpg" class="aspect-[2/3] w-56 rounded-lg object-cover" alt="Game">
			</div>
			<div class="flex flex-col gap-1">
				<img src="https://upload.wikimedia.org/wikipedia/en/0/0f/SpiderMan2PS5BoxArt.jpeg" class="aspect-[2/3] w-56 rounded-lg object-cover" alt="Game">
				<img src="https://upload.wikimedia.org/wikipedia/en/7/75/Battlefield_4_cover_art.jpg" class="aspect-[2/3] w-56 rounded-lg object-cover" alt="Game">
				<img src="https://upload.wikimedia.org/wikipedia/en/f/f1/TombRaider2013.jpg" class="aspect-[2/3] w-56 rounded-lg object-cover" alt="Game">
			</div>
		</div>
	</div>
</section>

<style>
	@keyframes slowScroll { from { transform: translateY(0); } to { transform: translateY(-33%); } }
	.animate-slowScroll { animation: slowScroll 40s linear infinite; }
	.animate-fadeUp { animation: fadeUp 0.7s cubic-bezier(0.16, 1, 0.3, 1) forwards; }
	@keyframes fadeUp { from { opacity: 0; transform: translateY(24px); } to { opacity: 1; transform: translateY(0); } }
</style>