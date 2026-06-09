<script lang="ts">
	import type { UserLogin } from "../../api/interface/UserLogin";
    import {loginUser} from "../../api/services/UserServices/Login"

	let showPassword = $state(false);

    let email = $state("");
    let password = $state("");

    function handleSubmit(e: Event) {
        e.preventDefault();

        const formBody: UserLogin = {
            email,
			password
		};

        loginUser(formBody)
			.then(() =>  {
                window.location.href="/";
			})
		.catch(error => {
            throw error;
		})
	}
</script>

<div class="flex min-h-screen items-center justify-center bg-[#12100f] px-6 py-12">
	<form
			onsubmit={handleSubmit}
			class="w-full max-w-md rounded-2xl border border-[rgba(242,237,231,0.07)] bg-[rgba(18,16,15,0.9)] p-10 backdrop-blur-[16px]"
	>
		<a href="/">
			<h2 class="mb-8 text-center text-2xl font-bold text-white">
				<span class="text-[#66c0f4]">Backlog</span><span>DB</span>
			</h2>
		</a>

		<div class="mb-5">
			<label for="email" class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">EMAIL</label>
			<input
					type="text"
					bind:value={email}
					name="email"
					placeholder="email@example.com"
					class="w-full rounded-lg border border-[rgba(242,237,231,0.1)] bg-transparent px-4 py-3 text-sm text-white outline-none transition focus:border-[#66c0f4]"
			/>
		</div>

		<div class="mb-2">
			<label for="password" class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">PASSWORD</label>
			<div class="relative w-full">
				<input
						type={showPassword ? 'text' : 'password'}
						bind:value={password}
						name="password"
						placeholder="••••••••"
						class="w-full rounded-lg border border-[rgba(242,237,231,0.1)] bg-transparent pl-4 pr-12 py-3 text-sm text-white outline-none transition focus:border-[#66c0f4]"
				/>
				<button type="button" onclick={() => showPassword = !showPassword} class="absolute right-4 top-1/2 -translate-y-1/2 text-[rgba(242,237,231,0.4)] hover:text-white transition bg-transparent border-none cursor-pointer">
					{#if showPassword}
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-5 h-5"><path stroke-linecap="round" stroke-linejoin="round" d="M3.98 8.223A10.477 10.477 0 0 0 1.934 12C3.226 16.338 7.244 19.5 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.451 10.451 0 0 1 12 4.5c4.756 0 8.773 3.162 10.065 7.498a10.522 10.522 0 0 1-4.293 5.774M6.228 6.228 17.772 17.772m-10.34-10.34A3 3 0 0 0 11.66 11.66m5.756 5.756a3 3 0 0 1-4.243-4.243" /></svg>
					{:else}
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-5 h-5"><path stroke-linecap="round" stroke-linejoin="round" d="M2.036 12.322a1.012 1.012 0 0 1 0-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178Z" /><path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" /></svg>
					{/if}
				</button>
			</div>
		</div>

		<a href="/forgot-password" class="mb-8 block text-right text-xs text-[rgba(242,237,231,0.45)] hover:text-[#66c0f4] transition">
			Forgot password?
		</a>

		<button type="submit" class="w-full rounded-full bg-[rgba(242,237,231,1)] py-3 text-sm font-bold text-[#12100f] transition hover:scale-[1.02] hover:bg-[#66c0f4] hover:cursor-pointer">
			SIGN IN
		</button>

		<a href="/register" class="mt-6 block text-center text-sm text-[rgba(242,237,231,0.6)] hover:text-white transition">
			No account yet? Register here.
		</a>
	</form>
</div>