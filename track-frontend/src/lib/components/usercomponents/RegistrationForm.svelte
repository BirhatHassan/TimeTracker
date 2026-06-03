<script>
	let username = "";
	let email = "";
	let password = "";

	function handleSubmit(e) {
		e.preventDefault();
		// handle register logic here
		if (password.length < 6) {
			alert("Please enter a valid email address");
		}
	}
</script>

<div class="flex min-h-screen items-center justify-center bg-[#12100f] px-6">
	<form
			on:submit={handleSubmit}
			class="w-full max-w-md rounded-2xl border border-[rgba(242,237,231,0.07)] bg-[rgba(18,16,15,0.9)] p-10 backdrop-blur-[16px]"
	>
		<h2 class="mb-8 text-center text-2xl font-bold text-white">
			<span class="italic text-[#66c0f4]">TIME</span><span>Tracker</span>
		</h2>

		<!-- Username -->
		<div class="mb-5">
			<label class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">
				USERNAME
			</label>
			<input
					id="username"
					type="text"
					bind:value={username}
					class="w-full rounded-lg border border-[rgba(242,237,231,0.1)] bg-transparent px-4 py-3 text-sm text-white outline-none transition focus:border-[#66c0f4] focus:ring-1 focus:ring-[#66c0f4]"
			/>
		</div>

		<!-- Email -->
		<div class="mb-5">
			<label class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">
				EMAIL
			</label>
			<input
					id="email"
					type="email"
					bind:value={email}
					class="w-full rounded-lg border border-[rgba(242,237,231,0.1)] bg-transparent px-4 py-3 text-sm text-white outline-none transition focus:border-[#66c0f4] focus:ring-1 focus:ring-[#66c0f4]"
			/>
		</div>

		<!-- Password -->
		<div class="mb-8">
			<label class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">
				PASSWORD
			</label>
			<input
					id="password"
					type="password"
					bind:value={password}
					class="w-full rounded-lg border border-[rgba(242,237,231,0.1)] bg-transparent px-4 py-3 text-sm text-white outline-none transition focus:border-[#66c0f4] focus:ring-1 focus:ring-[#66c0f4]"
			/>
		</div>

		<!-- Button -->
		<button
				type="submit"
				class="w-full rounded-full bg-[rgba(242,237,231,1)] py-3 text-sm font-bold text-[#12100f] transition duration-200 hover:scale-[1.02] hover:bg-[#66c0f4]"
		>
			REGISTER
		</button>

		<!-- Divider / Commented out for now: Putting back in with OAuth2.0 -->
<!--		<div class="mt-5 flex items-center gap-3">-->
<!--			<div class="h-px flex-1 bg-[rgba(242,237,231,0.1)]"></div>-->
<!--				<span class="text-[10px] tracking-[2px] text-[rgba(242,237,231,0.4)]">-->
<!--					OR-->
<!--				</span>-->
<!--			<div class="h-px flex-1 bg-[rgba(242,237,231,0.1)]"></div>-->
<!--		</div>-->

<!--		&lt;!&ndash; Google Button &ndash;&gt;-->
<!--		<button-->
<!--				type="button"-->
<!--				class="mt-5 flex w-full items-center justify-center gap-3 rounded-full border border-[rgba(242,237,231,0.1)] bg-[rgba(255,255,255,0.02)] py-3 text-sm font-medium text-white transition duration-200 hover:border-[#66c0f4] hover:bg-[rgba(102,192,244,0.08)]"-->
<!--		>-->
<!--			&lt;!&ndash; Google Icon &ndash;&gt;-->
<!--			<svg class="h-4 w-4" viewBox="0 0 48 48">-->
<!--				<img src="https://upload.wikimedia.org/wikipedia/commons/c/c1/Google_%22G%22_logo.svg">-->
<!--			</svg>-->

<!--			Continue with Google-->
<!--		</button>-->
	</form>
</div>