<script lang="ts">
    import {checkEmail} from '../../api/services/UserServices/CheckEmail';
    import {checkUsername} from '../../api/services/UserServices/CheckUsername';
    import {createUser} from "../../api/services/UserServices/CreateUser";

    import type {UserRegistration} from "../../api/interface/UserRegistration";

    let firstName = $state("");
    let lastName = $state("");
    let birthDate = $state("");
    let username = $state("");
    let email = $state("");
    let password = $state("");
    let showPassword = $state(false);
    let submitted = $state(false);

    let emailExists = $state(false);
    let usernameExists = $state(false);
    let checkingEmail = $state(false);
    let checkingUsername = $state(false);
    let hasChecked = $state(false);
    let hasCheckedUsername = $state(false);

    let emailTimeout: ReturnType<typeof setTimeout>;
    let usernameTimeout: ReturnType<typeof setTimeout>;

    let isPotentialEmail = $derived(email.includes("@") && email.includes("."));
    let isValidUsername = $derived(username.trim().length >= 3);

    const passwordRegex: RegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    let isValidPassword = $derived(passwordRegex.test(password));

    // Error messages
    let errors = $derived({
        firstName: submitted && !firstName.trim() ? "Voornaam is verplicht." : "",
        username: hasCheckedUsername && usernameExists
            ? "Deze gebruikersnaam is al in gebruik."
            : submitted && !isValidUsername
                ? "Gebruikersnaam moet minimaal 3 tekens zijn."
                : "",
        email: hasChecked && emailExists
            ? "Dit e-mailadres is al in gebruik."
            : submitted && !isPotentialEmail
                ? "Voer een geldig e-mailadres in."
                : "",
        password: submitted && !isValidPassword
            ? "Minimaal 8 tekens, een letter, een cijfer en een speciaal teken (@$!%*?&)."
            : "",
    });

    function handleEmailInput() {
        clearTimeout(emailTimeout);
        emailExists = false;
        hasChecked = false;
        if (!isPotentialEmail) {
            checkingEmail = false;
            return;
        }
        checkingEmail = true;
        emailTimeout = setTimeout(async () => {
            try {
                emailExists = await checkEmail(email);
                hasChecked = true;
            } catch (err) {
                console.error(err);
                emailExists = false;
            } finally {
                checkingEmail = false;
            }
        }, 400);
    }

    function handleUsernameInput() {
        clearTimeout(usernameTimeout);
        usernameExists = false;
        hasCheckedUsername = false;
        if (!isValidUsername) {
            checkingUsername = false;
            return;
        }
        checkingUsername = true;
        usernameTimeout = setTimeout(async () => {
            try {
                usernameExists = await checkUsername(username);
                hasCheckedUsername = true;
            } catch (err) {
                console.error(err);
                usernameExists = false;
            } finally {
                checkingUsername = false;
            }
        }, 400);
    }

    function handleSubmit(e: Event) {
        e.preventDefault();
        submitted = true;

        if (emailExists || usernameExists) return;
        if (!firstName.trim()) return;
        if (!isPotentialEmail || !isValidUsername || !isValidPassword) return;

        const formBody: UserRegistration = {
            firstName,
            lastName,
            email,
            username,
            password,
            dateOfBirth: birthDate
        };

        createUser(formBody)
            .then(() => {
                window.location.href = "/login";
            })
            .catch((err) => {
                console.error(err);
            });
    }
</script>

<div class="flex min-h-screen items-center justify-center bg-[#12100f] px-6 py-12">
	<form
			onsubmit={handleSubmit}
			class="w-full max-w-md rounded-2xl border border-[rgba(242,237,231,0.07)] bg-[rgba(18,16,15,0.9)] p-10 backdrop-blur-[16px]"
	>
		<a href="/">
			<h2 class="mb-8 text-center text-2xl font-bold text-white">
				<span class="italic text-[#66c0f4]">Backlog</span><span class="mx-0.5">DB</span>
			</h2>
		</a>

		<div class="grid grid-cols-2 gap-4 mb-5">
			<div>
				<label for="firstName" class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">FIRST</label>
				<input
						type="text"
						bind:value={firstName}
						placeholder="John"
						class="w-full rounded-lg border px-4 py-3 text-sm text-white bg-transparent outline-none transition
                        {submitted && !firstName.trim() ? 'border-red-500 focus:border-red-500' : 'border-[rgba(242,237,231,0.1)] focus:border-[#66c0f4]'}"
				/>
				{#if errors.firstName}
					<p class="mt-1 text-xs text-red-400">{errors.firstName}</p>
				{/if}
			</div>
			<div>
				<label for="lastName" class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">LAST</label>
				<input
						type="text"
						bind:value={lastName}
						placeholder="Doe"
						class="w-full rounded-lg border px-4 py-3 text-sm text-white bg-transparent outline-none transition border-[rgba(242,237,231,0.1)] focus:border-[#66c0f4]"
				/>
			</div>
		</div>

		<div class="mb-5">
			<label for="dateOfBirth" class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">BIRTH DATE</label>
			<input
					type="date"
					bind:value={birthDate}
					class="w-full rounded-lg border px-4 py-3 text-sm text-white bg-transparent outline-none transition [color-scheme:dark] border-[rgba(242,237,231,0.1)]"
			/>
		</div>

		<div class="mb-5">
			<label for="username" class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">USERNAME</label>
			<input
					type="text"
					bind:value={username}
					oninput={handleUsernameInput}
					placeholder="johndoe"
					class="w-full rounded-lg border px-4 py-3 text-sm text-white bg-transparent outline-none transition
                    {hasCheckedUsername && usernameExists ? 'border-red-500 focus:border-red-500' : ''}
                    {!checkingUsername && hasCheckedUsername && !usernameExists ? 'border-green-500 focus:border-green-500' : ''}
                    {submitted && !isValidUsername && !hasCheckedUsername ? 'border-red-500 focus:border-red-500' : ''}
                    {(!hasCheckedUsername || checkingUsername) && !(submitted && !isValidUsername) ? 'border-[rgba(242,237,231,0.1)] focus:border-[#66c0f4]' : ''}"
			/>
			{#if errors.username}
				<p class="mt-1 text-xs text-red-400">{errors.username}</p>
			{/if}
		</div>

		<div class="mb-5">
			<label for="emailAddress" class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">EMAIL</label>
			<input
					type="email"
					bind:value={email}
					oninput={handleEmailInput}
					placeholder="email@example.com"
					class="w-full rounded-lg border px-4 py-3 text-sm text-white bg-transparent outline-none transition
                    {hasChecked && emailExists ? 'border-red-500 focus:border-red-500' : ''}
                    {!checkingEmail && hasChecked && !emailExists ? 'border-green-500 focus:border-green-500' : ''}
                    {submitted && !isPotentialEmail && !hasChecked ? 'border-red-500 focus:border-red-500' : ''}
                    {(!hasChecked || checkingEmail) && !(submitted && !isPotentialEmail) ? 'border-[rgba(242,237,231,0.1)] focus:border-[#66c0f4]' : ''}"
			/>
			{#if errors.email}
				<p class="mt-1 text-xs text-red-400">{errors.email}</p>
			{/if}
		</div>

		<div class="mb-8">
			<label for="password" class="mb-2 block text-[11px] font-medium tracking-[2px] text-[rgba(242,237,231,0.6)]">PASSWORD</label>
			<div class="relative w-full">
				<input
						type={showPassword ? "text" : "password"}
						bind:value={password}
						placeholder="••••••••"
						class="w-full rounded-lg border pl-4 pr-12 py-3 text-sm text-white bg-transparent outline-none transition
                        {submitted && !isValidPassword ? 'border-red-500 focus:border-red-500' : 'border-[rgba(242,237,231,0.1)] focus:border-[#66c0f4]'}"
				/>
				<button type="button" onclick={() => showPassword = !showPassword} class="absolute right-4 top-1/2 -translate-y-1/2 text-[rgba(242,237,231,0.4)] hover:text-white transition bg-transparent border-none cursor-pointer">
					{#if showPassword}
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-5 h-5"><path stroke-linecap="round" stroke-linejoin="round" d="M3.98 8.223A10.477 10.477 0 0 0 1.934 12C3.226 16.338 7.244 19.5 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.451 10.451 0 0 1 12 4.5c4.756 0 8.773 3.162 10.065 7.498a10.522 10.522 0 0 1-4.293 5.774M6.228 6.228 17.772 17.772m-10.34-10.34A3 3 0 0 0 11.66 11.66m5.756 5.756a3 3 0 0 1-4.243-4.243" /></svg>
					{:else}
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-5 h-5"><path stroke-linecap="round" stroke-linejoin="round" d="M2.036 12.322a1.012 1.012 0 0 1 0-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178Z" /><path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" /></svg>
					{/if}
				</button>
			</div>
			{#if errors.password}
				<p class="mt-1 text-xs text-red-400">{errors.password}</p>
			{/if}
		</div>

		<button type="submit" class="w-full rounded-full bg-[rgba(242,237,231,1)] py-3 text-sm font-bold text-[#12100f] transition hover:scale-[1.02] hover:bg-[#66c0f4] hover:cursor-pointer">
			REGISTER
		</button>

		<a href="/" class="mt-6 block text-center text-sm text-[rgba(242,237,231,0.6)] hover:text-white transition">
			Sign in here.
		</a>
	</form>
</div>